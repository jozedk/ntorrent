# Introduction #

A quick howto on compiling and installing rtorrent, libtorrent and xmlrpc-c.

if you already have a working and configured rTorrent installation, continue to [SettingUpAConnection](SettingUpAConnection.md) or/and [StartingnTorrent](StartingnTorrent.md)

This guide expects that your system has all the installed dependencies for compiling theese sources, therefore i have not included such installation steps.

# Downloading #

download the source files, like so.
```
root@netbox:~# cd /usr/src/
root@netbox:/usr/src# svn checkout https://xmlrpc-c.svn.sourceforge.net/svnroot/xmlrpc-c/advanced xmlrpc-c
root@netbox:/usr/src# wget http://libtorrent.rakshasa.no/downloads/libtorrent-0.11.9.tar.gz
root@netbox:/usr/src# wget http://libtorrent.rakshasa.no/downloads/rtorrent-0.7.9.tar.gz
```

(Alternative, svn version of rtorrent/libtorrent)
```
root@netbox:/usr/src# svn co http://rakshasa.no/libtorrent/trunk/rtorrent rtorrent
root@netbox:/usr/src# svn co http://rakshasa.no/libtorrent/trunk/libtorrent libtorrent
```

now unpack the sources.

```
root@netbox:/usr/src# tar xzvf libtorrent*
root@netbox:/usr/src# tar xzvf rtorrent*
```


# Compiling and installing #

## xmlrpc-c ##
_Please note that you need a xmlrpc-c version greater than 1.07_
```
root@netbox:/usr/src/libtorrent-0.11.8# cd ../xmlrpc-c
root@netbox:/usr/src/xmlrpc-c-1.11.00# ./configure
root@netbox:/usr/src/xmlrpc-c-1.11.00# make
root@netbox:/usr/src/xmlrpc-c-1.11.00# make install
```

## libtorrent ##
```
root@netbox:/usr/src# cd libtorrent*/
# if you have the svn version you need to issue "sh autogen.sh" to generate the configure file.
root@netbox:/usr/src/libtorrent-0.11.8# ./configure 
root@netbox:/usr/src/libtorrent-0.11.8# make
root@netbox:/usr/src/libtorrent-0.11.8# make install
```

## rtorrent ##
```
root@netbox:/usr/src/xmlrpc-c-1.11.00# cd ../rtorrent*
# if you have the svn version you need to issue "sh autogen.sh" to generate the configure file.
root@netbox:/usr/src/rtorrent-0.7.8# ./configure --with-xmlrpc-c
# if you get no package 'libtorrent' found, you might need something like this:
# PKG_CONFIG_PATH="/usr/local/lib/pkgconfig" ./configure --with-xmlrpc-c
# also you should run ldconfig to update libraries (needs pkg-config)
root@netbox:/usr/src/rtorrent-0.7.8# make
root@netbox:/usr/src/rtorrent-0.7.8# make install
```

Put this in your ~/.rtorrent.rc:
```
# This will set rtorrent/scgi to listen on localhost, port 5000.
scgi_port = 127.0.0.1:5000
#force UTF-8, as xmlrpc-c only transfers in UTF-8
encoding_list = UTF-8
```

Looks like we made it!

Now you can continue to [SettingUpAConnection](SettingUpAConnection.md) or/and [StartingnTorrent](StartingnTorrent.md).

# Checking if xmlrpc works #
At cmd line enter:
```
xmlrpc localhost system.listMethods
```
if it outputs a list, then xmlrcp works.

If you get an error like: libxmlrpc.so.3 not found
then add the line: `/usr/local/lib` to the file `/etc/ld.so.conf`,
and run `sudo ldconfig`.

# Final notes #

For more information on configuring rtorrent with xmlrpc take a look at http://libtorrent.rakshasa.no/wiki/RTorrentXMLRPCGuide