# Starting nTorrent-0.4 #

## Prerequisites ##
  * [java](http://java.sun.com/)

## Introduction ##
nTorrent requires java >= 1.6, or java 1.5 as of Thursday, December 20 2007.

You can assosciate ".torrent" files with nTorrent, by setting nTorrent.jar or nTorrent.sh as the default launcher for torrent files. nTorrent will then upload the torrents to the remote rtorrent process.

## Linux ##
java1.6 will probably exist somewhere in your native packaging system. if not, you can download the source from the link above under prerequisites.

to start nTorrent you can do the following:

```
# java -jar nTorrent.jar [file.torrent]
```
or
```
# ./nTorrent.sh [file.torrent]
```
## Mac ##
There is no official java 1.6 release for mac users, but there exists a developer preview that works just aswell.

java 6 is supposed to be downloadable on the Apple Developer Connection site, at least it was for a couple of months ago, but now i couldn't seem to find it.
On your marks, get set, start googling!

Starting ntorrent should be the same as in the case of Linux.

## Windows ##
Windows users must download java 1.6 from the link mentioned in prerequisites to run nTorrent.

nTorrent can be started by doubleclicking the nTorrent.jar file. To assosciate torrent files with nTorrent you have to do this through the windows filemanager by setting nTorrent.jar as the default launcher of .torrent files.