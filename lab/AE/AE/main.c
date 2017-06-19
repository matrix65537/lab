#include <stdio.h>
#include <WinSock.h>
#include "ae.h"

#define LISTEN_PORT 9090

#define TIMER1_MS	4000
#define TIMER2_MS	1000

int Timer1(struct aeEventLoop* eventLoop, long long id, void* clientData)
{
	printf("Timer1\n");
	return TIMER1_MS;
}

int Timer2(struct aeEventLoop* eventLoop, long long id, void* clientData)
{
	printf("Timer2\n");
	return TIMER2_MS;
}

int main()
{
	int server_sock_fd;
	struct sockaddr_in server_addr;
	int server_addr_len;
	char on = 1;
	int r;
	aeEventLoop* eventLoop = aeCreateEventLoop();

	WSADATA wsaData;
	WSAStartup(0x0202, &wsaData);

	server_sock_fd = socket(AF_INET, SOCK_STREAM, 0);
	if (server_sock_fd == INVALID_SOCKET)
	{
		exit(1);
	}

	if (setsockopt(server_sock_fd, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on)) == SOCKET_ERROR)
	{
		exit(1);
	}

	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	server_addr.sin_port = htons(LISTEN_PORT);

	server_addr_len = sizeof(server_addr);
	r = bind(server_sock_fd, (struct sockaddr*)&server_addr, server_addr_len);
	if (r == SOCKET_ERROR)
	{
		exit(1);
	}
	r = listen(server_sock_fd, 0x100);
	if (r == SOCKET_ERROR)
	{
		exit(1);
	}

	aeCreateFileEvent(eventLoop, server_sock_fd, AE_READABLE, NULL, NULL);

	aeCreateTimeEvent(eventLoop, TIMER1_MS, Timer1, NULL, NULL);
	aeCreateTimeEvent(eventLoop, TIMER2_MS, Timer2, NULL, NULL);

	aeMain(eventLoop);

	return 0;
}