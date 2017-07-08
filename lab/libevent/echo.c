#include <string.h>
#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/socket.h>

#include <event2/bufferevent.h>
#include <event2/buffer.h>
#include <event2/listener.h>
#include <event2/util.h>
#include <event2/event.h>

static const char MESSAGE[] = "Hello, World!\n";
static const int PORT = 9995;

static void listener_cb(struct evconnlistener *listener, evutil_socket_t fd, struct sockaddr *sa, int socklen, void *user_data);

static void conn_writecb(struct bufferevent *bev, void *user_data);

static void conn_eventcb(struct bufferevent *bev, short events, void *user_data);

static int count = 0;

int main(int argc, char **argv)
{
    struct event_base *base;
    struct evconnlistener *listener;
    struct event *signal_event;
    struct sockaddr_in sin;

    count = 0;
    base = event_base_new();
    if(!base)
    {
        fprintf(stderr, "Could not initialize libevent!\n");
        return 1;
    }

    memset(&sin, 0x00, sizeof(sin));
    sin.sin_family = AF_INET;
    sin.sin_port = htons(PORT);

    listener = evconnlistener_new_bind(base, listener_cb, (void*)base, LEV_OPT_REUSEABLE | LEV_OPT_CLOSE_ON_FREE, -1, (struct sockaddr*)&sin, sizeof(sin));

    if(!listener)
    {
        fprintf(stderr, "Could not create a listener!\n");
        return 1;
    }

    printf("Waitting...\n");
    printf("%p\n", listener);
    event_base_dispatch(base);

    evconnlistener_free(listener);
    event_base_free(base);

    printf("Done\n");
    return 0;
}

static void listener_cb(struct evconnlistener *listener, evutil_socket_t fd, struct sockaddr *sa, int socklen, void *user_data)
{
    struct event_base *base = user_data;
    struct bufferevent *bev;

    count++;
    printf("New connection comming, count = %d\n", count);

    //bufferevent create and associate it with fd
    bev = bufferevent_socket_new(base, fd, BEV_OPT_CLOSE_ON_FREE);
    if(!bev)
    {
        fprintf(stderr, "Error constructing bufferevent!");
        event_base_loopbreak(base);
        return;
    }
    bufferevent_free(bev);
    return;

    bufferevent_setcb(bev, NULL, conn_writecb, conn_eventcb, NULL);

    bufferevent_enable(bev, EV_WRITE);
    bufferevent_disable(bev, EV_READ);

    bufferevent_write(bev, MESSAGE, strlen(MESSAGE));
}


static void conn_writecb(struct bufferevent *bev, void *user_data)
{
    struct evbuffer *output = bufferevent_get_output(bev);
    if(evbuffer_get_length(output) == 0)
    {
        printf("flushed answer\n");
        bufferevent_free(bev);
        count--;
    }
}

static void conn_eventcb(struct bufferevent *bev, short events, void *user_data)
{
    if(events & BEV_EVENT_EOF)
    {
        printf("Connection closed.\n");
        count--;
    }
    else if(events & BEV_EVENT_ERROR)
    {
        printf("Got an error on the connection: %s\n", strerror(errno));
        count--;
    }

    bufferevent_free(bev);
}

