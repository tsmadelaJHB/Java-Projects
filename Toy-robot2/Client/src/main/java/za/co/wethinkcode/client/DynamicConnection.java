package za.co.wethinkcode.client;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class DynamicConnection {
    public static List<String> IPaddress() throws IOException {
        /*
         * Get the config of the geteway device
         * Strip Local IP allocated to the divice by Router(DHCP).
         * create a search list of IP addresses narrowed down to current class
         * Check from the ist if IP is reachable
         * */

        List<String> localIPAddressList = new ArrayList<>();

        //Get Ip addresses assigned to local devices
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                localIPAddressList.add(i.getHostAddress().toLowerCase(Locale.ROOT));
            }
        }


        //Create range of local addresses
        String[] IPaddress;
        String localIP = "";
        if (localIPAddressList.get(1).split("\\.").length > 1) {
            if (localIPAddressList.get(1).split("\\.")[0] != "127"){
                IPaddress = localIPAddressList.get(1).split("\\.");
                localIP = IPaddress[0] + "." + IPaddress[1] + "." + IPaddress[2] + ".";
            }
        }
        //Clear list to create IP addresses Listening in port 5000
        localIPAddressList.clear();

        //Create list of IP Addresses to search
        localIPAddressList.add("127.0.0.1");
        int index = 255;
        while(index > 0) {
            localIPAddressList.add (localIP + index);
            index--;
        }

        //Search address with listening port and create list
        List <String> hostIP = new ArrayList<>();
        for (String i : localIPAddressList){
            String host = checkHosts(i);
            if (host != "") {
                hostIP.add(host);
                // System.out.print(index);
            }
        }
        return hostIP;
    }

    private static String checkHosts(String host) throws IOException {
        /*
         * Check if the IP Address is reachable
         * TODO: if the IP Address is reachable, it further check
         *   if it is listing thought port 5000 by calling
         *       Port5000Open() Function.
         * */

        InetAddress addr = InetAddress.getByName( host);
        int timeout=2;
        if (InetAddress.getByName(host).isReachable(timeout)){
            if (port5000Open(host)) {
                return host;
            }
            return "";
        }
        return "";
    }

    private static boolean port5000Open(String IP){
        /*
         * Given IP Address, Method check if it listening through port 5000
         * Return true if listening and False if not
         * */


        try
        {
            Socket ServerSok = new Socket(IP, 5000);
            // ServerSok.close();
            System.out.println("port 5000 is open");
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
