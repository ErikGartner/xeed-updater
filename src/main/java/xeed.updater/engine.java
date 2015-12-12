/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xeed.updater;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Erik
 */
public class Engine implements Runnable {

    final String szUpdateURL;
    public String szFile = "";
    private XeedUpdater xHandle = null;
    private long lngFileSize = 0;

    public Engine(String file, XeedUpdater x, String url) {
        szFile = file;
        xHandle = x;
        szUpdateURL = url;
    }

    public void run() {

        if (ApplyUpdate((szUpdateURL))) {
            xHandle.setProgress(10000, "Done!");
        } else {
            xHandle.setProgress(0, "An Error Occured And A Rollback Has Been Performed.");
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
            }
        }

        try {
            Process ps = Runtime.getRuntime().exec(new String[]{"java", "-jar", szFile, "/del_ud:\"" + new File(Engine.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath() + "\""});
        } catch (Exception e) {
        }
        System.exit(0);
    }

    private boolean ApplyUpdate(String szUrl) {

        File orgFile = null;
        File newFile = null;

        try {

            xHandle.setProgress(0, "Downloading Update...");

            orgFile = new File(szFile);
            newFile = new File(szFile + ".bak");
            newFile.delete();
            if (!orgFile.renameTo(newFile)) {
                JOptionPane.showConfirmDialog(null, "XEED couldn't gain write access to your XEED file.\nPlease make sure that other copies of XEED aren't running.\nThen press 'OK'.", "Please close other copies of XEED.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                if (!orgFile.renameTo(newFile)) {
                    return false;
                }
            }
            orgFile = new File(szFile + ".bak");
            newFile = new File(szFile);
            newFile.delete();

            URLConnection urlconUpdate = new URL(szUrl).openConnection();
            lngFileSize = urlconUpdate.getContentLength();

            BufferedInputStream in = new java.io.BufferedInputStream(urlconUpdate.getInputStream());
            FileOutputStream fos = new java.io.FileOutputStream(newFile);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);

            int count;
            byte data[] = new byte[4096];

            double chunkpercentage = 0;
            if (lngFileSize != -1) {
                chunkpercentage = 9000 / (double) (lngFileSize / 4096);
            } else {
                xHandle.setIndeterminate(true);
            }

            int x = 0;
            while ((count = in.read(data)) >= 0) {
                bout.write(data, 0, count);
                data = new byte[4096];

                if (lngFileSize != -1) {
                    xHandle.setProgress((int) (x * chunkpercentage), "Downloading Update...");
                }
                x++;
            }

            if (lngFileSize == -1) {
                xHandle.setIndeterminate(false);
            }

            xHandle.setProgress(9800, "Applying Update...");

            bout.close();
            fos.close();
            in.close();

        } catch (Exception e) {
            orgFile.renameTo(newFile);
            return false;
        }

        try {
            orgFile.delete();
            return true;
        } catch (Exception e) {
            orgFile.renameTo(newFile);
            return false;
        }
    }

    public String[] GetElements(String szData, String szElement) {

        int intEndPos;

        ArrayList<String> szElements = new ArrayList(0);

        String szRet[] = szData.split("<" + szElement + ">");
        for (int x = 0; x < szRet.length; x++) {

            intEndPos = szRet[x].indexOf("</" + szElement + ">");
            if (intEndPos != -1) {
                szElements.add(szRet[x].substring(0, intEndPos));
            }
        }
        String str[] = new String[szElements.size()];
        szElements.toArray(str);

        return str;
    }
}
