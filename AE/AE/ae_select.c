#include <string.h>
#include <stdlib.h>
#include <WinSock.h>

typedef struct aeApiState{
	fd_set rfds, wfds;
	fd_set _rfds, _wfds;
} aeApiState;

static int aeApiCreate(aeEventLoop* eventLoop)
{
	aeApiState* state = (aeApiState*)malloc(sizeof(aeApiState));

	if(!state)
	{
		return -1;
	}

	FD_ZERO(&state->rfds);
	FD_ZERO(&state->wfds);
	eventLoop->apidata = state;
	return 0;
}

static void aeApiFree(aeEventLoop* eventLoop)
{
	free(eventLoop->apidata);
}

static int aeApiAddEvent(aeEventLoop* eventLoop, int fd, int mask)
{
	aeApiState* state = eventLoop->apidata;

	if(mask & AE_READABLE)
	{
		FD_SET(fd, &state->rfds);
	}
	if (mask & AE_WRITABLE)
	{
		FD_SET(fd, &state->wfds);
	}
	return 0;
}

static void aeApiDelEvent(aeEventLoop* eventLoop, int fd, int mask)
{
	aeApiState* state = eventLoop->apidata;

	if (mask & AE_READABLE)
	{
		FD_CLR(fd, &state->rfds);
	}
	if(mask & AE_WRITABLE)
	{
		FD_CLR(fd, &state->wfds);
	}
}

static int aeApiPoll(aeEventLoop* eventLoop, struct timeval* tvp)
{
	aeApiState* state = eventLoop->apidata;
	int retval, j, numevents = 0;

	memcpy(&state->_rfds, &state->rfds, sizeof(fd_set));
	memcpy(&state->_wfds, &state->wfds, sizeof(fd_set));

	retval = select(eventLoop->maxfd + 1, &state->_rfds, &state->_wfds, NULL, tvp);
	if(retval > 0)
	{
		for (j = 0; j <= eventLoop->maxfd; ++j)
		{
			int mask = 0;
			aeFileEvent* fe = &eventLoop->events[j];
			if (fe->mask == AE_NONE)
			{
				continue;
			}
			if ((fe->mask & AE_READABLE) && (FD_ISSET(j, &state->_rfds)))
			{
				mask |= AE_READABLE;
			}
			if ((fe->mask & AE_WRITABLE) && (FD_ISSET(j, &state->_wfds)))
			{
				mask |= AE_WRITABLE;
			}
			eventLoop->fired[numevents].fd = j;
			eventLoop->fired[numevents].mask = mask;
			numevents++;
		}
	}

	return numevents;
}

static char* aeApiName(void)
{
	return "select";
}

#define DELTA_EPOCH_IN_MICROSECS  11644473600000000Ui64
int gettimeofday(struct timeval *tv)
{
	FILETIME ft;
	unsigned __int64 tmpres = 0;
	static int tzflag;

	if (NULL != tv)
	{
		GetSystemTimeAsFileTime(&ft);

		tmpres |= ft.dwHighDateTime;
		tmpres <<= 32;
		tmpres |= ft.dwLowDateTime;

		/*converting file time to unix epoch*/
		tmpres -= DELTA_EPOCH_IN_MICROSECS; 
		tmpres /= 10;  /*convert into microseconds*/
		tmpres &= 0xFFFFFFFFFFFF;
		tv->tv_sec = (long)(tmpres / 1000000UL);
		tv->tv_usec = (long)(tmpres % 1000000UL);
	}
	return 0;
}