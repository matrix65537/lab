#include <stdio.h>
#include <WinSock.h>
#include <stdint.h>
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
	printf("        Timer2\n");
	return TIMER2_MS;
}

#define BUF_SIZE 0x10
void ReadProc(struct aeEventLoop* eventLoop, int fd, void* clientData, int mask)
{
	uint8_t buf[BUF_SIZE + 1];
	int ret;

	memset(buf, 0x00, sizeof(buf));
	printf("ReadProc\n");
	ret = recv(fd, buf, BUF_SIZE, 0);
	if(ret <= 0)
	{
		printf("recv error\n");
		closesocket(fd);
	}
	else
	{
		printf("%s\n", (char*)buf);
	}
}

void ListenProc(struct aeEventLoop* eventLoop, int fd, void* clientData, int mask)
{
	int ret;
	struct sockaddr_in client_addr;
	int sock_addr_len = sizeof(client_addr);

	ret = accept(fd, (struct sockaddr*)&client_addr, &sock_addr_len); 
	if(ret == INVALID_SOCKET)
	{
		printf("accept error.\n");
	}
	else
	{
		printf("accept success.\n");
		aeCreateFileEvent(eventLoop, ret, AE_READABLE, ReadProc, NULL);
	}
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

	aeCreateFileEvent(eventLoop, server_sock_fd, AE_READABLE, ListenProc, NULL);

	aeCreateTimeEvent(eventLoop, TIMER1_MS, Timer1, NULL, NULL);
	aeCreateTimeEvent(eventLoop, TIMER2_MS, Timer2, NULL, NULL);

	aeMain(eventLoop);

	return 0;
}