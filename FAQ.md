# FAQ nTorrent 0.5 #
  1. **I can't save profile (Mac OSX)**
    * The apple laf isn't implemented correctly in java, causing notSerializableException when trying to save a profile. Try changing the skin to say metal and save profile.
  1. **I get a blank error message dialogue window when trying to connect.**
    * Check the log files and see what error message was thrown further down in the exception stack. Usually this is because you have an old version of xmlrpc-c. read the installation guide on this.
  1. **Problem with large .torrent files? (XML-RPC request too large).**
    * look at [Issue 25](https://code.google.com/p/ntorrent/issues/detail?id=25)
  1. **Suddenly have no torrents listed in nTorrent even though they exist in rTorrent.**
    * You have a torrent file that isn't using UTF-8 encoding, you should add this too your .rtorrent.rc config file: encoding\_list = UTF-8, this will force UTF-8 communcation over XMLRPC
  1. **nTorrent 0.5 fails to open in OSX 10.5.5 (Java 1.6 update installed)**
    * look at [Issue 107](https://code.google.com/p/ntorrent/issues/detail?id=107)
more to come...