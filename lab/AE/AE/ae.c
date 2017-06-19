#include <stdlib.h>
#include "ae.h"

#include "ae_select.c"

aeEventLoop* aeCreateEventLoop(void)
{
	aeEventLoop* eventLoop;
	int i;

	eventLoop = (aeEventLoop*)malloc(sizeof(*eventLoop));
	if (!eventLoop)
	{
		return NULL;
	}

	eventLoop->timeEventHead = NULL;
	eventLoop->timeEventNextId = 0;
	eventLoop->stop = 0;
	eventLoop->maxfd = -1;
	eventLoop->beforesleep = NULL;
	if (aeApiCreate(eventLoop) == -1)
	{
		free(eventLoop);
		return NULL;
	}
	
	for (i = 0; i < AE_SETSIZE; ++i)
	{
		eventLoop->events[i].mask = AE_NONE;
	}
	return eventLoop;
	
}

void aeDeleteEventLoop(aeEventLoop* eventLoop)
{
	aeApiFree(eventLoop);
}

void aeStop(aeEventLoop* eventLoop)
{
	eventLoop->stop = 1;
}

int aeCreateFileEvent(aeEventLoop* eventLoop, int fd, int mask, aeFileProc* proc, void* clientData)
{
	aeFileEvent* fe;

	if(fd >= AE_SETSIZE)
	{
		return AE_ERR;
	}

	fe = &eventLoop->events[fd];

	if (aeApiAddEvent(eventLoop, fd, mask) == -1)
	{
		return AE_ERR;
	}
	fe->mask |= mask;
	if(mask & AE_READABLE)
	{
		fe->rfileProc = proc;
	}
	if(mask & AE_WRITABLE)
	{
		fe->wfileProc = proc;
	}
	fe->clientData = clientData;
	if(fd > eventLoop->maxfd)
	{
		eventLoop->maxfd = fd;
	}

	return AE_OK;
}

void aeDeleteFileEvent(aeEventLoop* eventLoop, int fd, int mask)
{
	aeFileEvent* fe;

	if(fd >= AE_SETSIZE)
	{
		return;
	}

	fe = &eventLoop->events[fd];

	if(fe->mask == AE_NONE)
	{
		return;
	}

	fe->mask = fe->mask & (~mask);
	if ((fd == eventLoop->maxfd) && (fe->mask == AE_NONE))
	{
		int j;
		for (j = eventLoop->maxfd - 1; j >= 0; j--)
		{
			if (eventLoop->events[j].mask != AE_NONE)
			{
				break;
			}
			eventLoop->maxfd = j;
		}
	}
	aeApiDelEvent(eventLoop, fd, mask);
}

long long aeCreateTimeEvent(aeEventLoop* eventLoop, long long milliseconds, aeTimeProc* proc, void* clientData, aeEventFinalizerProc* finalizerProc)
{
    return 0;
}

int aeDeleteTimeEvent(aeEventLoop* eventLoop, long long id)
{
    return 0;
}

int aeProcessEvents(aeEventLoop* eventLoop, int flags)
{
    return 0;
}

int aeWait(int fd, int mask, long long milliseconds)
{
    return 0;
}

void aeMain(aeEventLoop* eventLoop)
{
	eventLoop->stop = 0;
	while (!eventLoop->stop)
	{
		if(eventLoop->beforesleep)
		{
			eventLoop->beforesleep(eventLoop);
		}
		aeProcessEvents(eventLoop, AE_ALL_EVENTS);
	}
	
}

char* aeGetApiName(void)
{
	return aeApiName();
}

void aeSetBeforeSleepProc(aeEventLoop* eventLoop, aeBeforeSleepProc* beforesleep)
{
	eventLoop->beforesleep = beforesleep;
}
