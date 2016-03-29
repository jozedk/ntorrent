# Introduction #

For being able to connect to a _rTorrent_ on an other mashine

there are a quite few thinks that has to work.

This guide should help a bit.

**Note: this guide is yet incomplete!**

# Details #

You will need the following things.
  1. one rtorrent installation with XMLRPC support
  1. correct rtorrent configuration (~/.rtorrent.rc)
  1. Enviromnent to support either the HTTP, SSH or Local protocol of nTorrent.
  * HTTP, demands a webserver with scgi support (ex. apache)
  * SSH, demands an installed SSH server configured to support text passwords and port forwarding
  * Local, only demands the scgi socket to be created by rtorrent.

You can read about these in separate sections below.

## installing rTorrent ##
You can read more about this at: [QuickStart](QuickStart.md)

## rTorrent settings ##
You will need an .rtorrent.rc file in you home dir.
The following has to be set:
> scgi\_port = localhost:5000

Note: The port don't have to be 5000, any other unused port number can be used instead.

## setting up apache with SCGI ##
1. You will need the packages:
libapache2-mod-scgi python-scgi

2. After installing these packages,
you will need to enable this module.
You have to create a link in the dir
> _/etc/apache2/mods-enabled_ named _scgi.load_
pointing to
> _/etc/apache2/mods-available/scgi.load_

3.
Add the following line to _/etc/apache/sites-available/default_ inside the _VirtualHost_:
> SCGIMount /RPC2 127.0.0.1:5000

4. restart apache for the changes to take effect
> /etc/init.d/apache2 restart

You should be now ready to connect with ntorrent.

You can check the listening ports with:
> netstat -l
You should see something like this:
```
  Active Internet connections (only servers)
  Proto Recv-Q Send-Q Local Address           Foreign Address         State      
  tcp        0      0 *:6666                  *:*                     LISTEN     
  tcp        0      0 localhost.localdo:mysql *:*                     LISTEN     
  tcp        0      0 *:www                   *:*                     LISTEN     
  tcp        0      0 *:rplay                 *:*                     LISTEN     
  tcp        0      0 *:ssh                   *:*                     LISTEN     
  tcp        0      0 localhost.localdom:smtp *:*                     LISTEN     
```
In this example 6666 is where rtorrent communicates with other torrent clients.

## Setting up ssh server ##
Use your package manager to download openssh-server, this example uses a debian distro.
```
  apt-get install openssh-server
```
If connection through ssh doesn't work on first try then there most likely is something misconfigured in /etc/ssh/sshd\_config.

make sure PasswordAuthentication is set to yes in sshd\_config.
"PasswordAuthentication yes"


## setting up an nTorrent connection ##
```
protocol: HTTP
host: IP to rtorrent server (or FQDN of the host, given you have a dns server that resolves the correct ip.)
port: 80 (not port 5000 as apache points to port 5000 from port 80)
mount point: /RPC2 (the same as in apache config)
user: ???
pass: ???
```