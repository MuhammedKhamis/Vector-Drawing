package eg.edu.alexu.csd.oop.paint;


import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

public class Loader {

	private String pathToJar;

	public Constructor<ShapeIF> invokeClassMethod(String loadedClassName) {

		try {
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				// -6 because of .class
				String className = je.getName().substring(0, je.getName().length() - 6);
				// System.out.println(className);
				className = className.replace('/', '.');
				// System.out.println(className);
				@SuppressWarnings("unchecked")
				Class<ShapeIF> c = (Class<ShapeIF>) cl.loadClass(className);
				className = className.substring(className.lastIndexOf('.') + 1, className.length());
				if (loadedClassName.contains(className)) {
					Constructor<ShapeIF> constructor = c.getConstructor(int[].class, int[].class);
					return constructor;
				}

			}
			jarFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String[] names() {
		String[] names = new String[200];
		int cnt = 0;
		try {
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				// -6 because of .class
				String className = je.getName().substring(0, je.getName().length() - 6);
				className = className.replace('/', '.');
				// System.out.println(className);
				@SuppressWarnings("unchecked")
				Class<ShapeIF> c = (Class<ShapeIF>) cl.loadClass(className);
				try {
					Constructor<ShapeIF> constructor = c.getConstructor(int[].class, int[].class);
					className = className.substring(className.lastIndexOf('.') + 1, className.length());
					names[cnt] = className;
					cnt++;
				} catch (Exception e1) {
					// e1.printStackTrace();
				}

			}
			jarFile.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "You must select .jar file.");
		}
		String[] res = new String[cnt];
		for (int i = 0; i < cnt; i++) {
			res[i] = names[i];
		}
		return res;
	}

	public String getPathToJar() {
		return pathToJar;
	}

	public void setPathToJar(String pathToJar) {
		this.pathToJar = pathToJar;
	}
}
