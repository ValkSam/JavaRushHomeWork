package myTests.plugin.hello;

import plugingIntrfc.Plugin;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Valk on 16.07.15.
 */
public class PluginTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        //
        {
            File pluginDir = new File("D:/Plugins");

            File[] jars = pluginDir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isFile() && file.getName().endsWith(".jar");
                }
            });
            Class[] pluginClasses = new Class[jars.length];
            for (int i = 0; i < jars.length; i++) {
                URL jarURL = jars[i].toURI().toURL();
                //URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL}); // или так:
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarURL});
                pluginClasses[i] = classLoader.loadClass("HelloPlugin");
            }
            //
            for (Class clazz : pluginClasses) {
                try {
                    Plugin instance = (Plugin) clazz.newInstance();
                    instance.invoke();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //
        /*
        {
            File pluginDir = new File("D:/Plugins/");
            URL jarURL = pluginDir.toURI().toURL();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarURL});
            Class[] pluginClasses = classLoader.find("HelloPlugin");

            for (int i = 0; i < jars.length; i++) {
                try {
                    URL jarURL = jars[i].toURI().toURL();
                    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarURL});
                    pluginClasses[i] = classLoader.loadClass("HelloPlugin");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //
            for (Class clazz : pluginClasses) {
                try {
                    Plugin instance = (Plugin) clazz.newInstance();
                    instance.invoke();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        */
    }
}
