/* org.agiso.tempel.JarTemplateSourceUTest (21-12-2012)
 * 
 * JarTemplateSourceUTest.java
 * 
 * Copyright 2012 agiso.org
 */
package org.agiso.tempel;

import org.agiso.tempel.api.ITemplateSource;
import org.agiso.tempel.api.ITemplateSourceEntry;
import org.agiso.tempel.core.JarTemplateSource;
import org.agiso.tempel.test.AbstractRepositoryTest;
import org.testng.annotations.Test;

/**
 * 
 * 
 * @author <a href="mailto:kkopacz@agiso.org">Karol Kopacz</a>
 */
public class JarTemplateSourceUTest extends AbstractRepositoryTest {
	@Test
	public void testFileSource() throws Exception {
		final String templatePath = "JarTemplateSourceUTest/testFileSource.jar";
		final String resourceName = /* TEMPEL-INF/template/ */ "testSourceFile1.txt";

		ITemplateSource templateSource = new JarTemplateSource(
				repositoryPath, templatePath, resourceName);

		assert repositoryPath.equals(templateSource.getRepository());
		assert templatePath.equals(templateSource.getTemplate());
		assert resourceName.equals(templateSource.getResource());
		assert true == templateSource.exists();
		assert true == templateSource.isFile();
		assert false == templateSource.isDirectory();
		assert 1 == templateSource.listEntries().size();

		ITemplateSourceEntry entry = templateSource.getEntry(resourceName);
		assert null != entry;
		assert templatePath.equals(entry.getTemplate());
		assert "testSourceFile1.txt".equals(entry.getName());
		assert true == entry.exists();
		assert true == entry.isFile();
		assert false == entry.isDirectory();
	}

	@Test
	public void testDirectorySource() throws Exception {
		final String templatePath = "JarTemplateSourceUTest/testDirectorySource.jar";

		ITemplateSource templateSource = new JarTemplateSource(
				repositoryPath, templatePath, null);

		assert repositoryPath.equals(templateSource.getRepository());
		assert templatePath.equals(templateSource.getTemplate());
		assert null == templateSource.getResource();
		assert true == templateSource.exists();
		assert false == templateSource.isFile();
		assert true == templateSource.isDirectory();
		assert 4 == templateSource.listEntries().size();

		ITemplateSourceEntry entry = templateSource.getEntry("/");
		assert null != entry;
		assert templatePath.equals(entry.getTemplate());
		assert "/".equals(entry.getName());
		assert true == entry.exists();
		assert false == entry.isFile();
		assert true == entry.isDirectory();

		entry = templateSource.getEntry("testSourceFile1.txt");
		assert null != entry;
		assert templatePath.equals(entry.getTemplate());
		assert "testSourceFile1.txt".equals(entry.getName());
		assert true == entry.exists();
		assert true == entry.isFile();
		assert false == entry.isDirectory();

		entry = templateSource.getEntry("subdirectory/");
		assert null != entry;
		assert templatePath.equals(entry.getTemplate());
		assert "subdirectory/".equals(entry.getName());
		assert true == entry.exists();
		assert false == entry.isFile();
		assert true == entry.isDirectory();

		entry = templateSource.getEntry("subdirectory/testSourceFile2.txt");
		assert null != entry;
		assert templatePath.equals(entry.getTemplate());
		assert "subdirectory/testSourceFile2.txt".equals(entry.getName());
		assert true == entry.exists();
		assert true == entry.isFile();
		assert false == entry.isDirectory();
	}

	@Test
	public void testDirectorySourceSubdirectory() throws Exception {
		final String templatePath = "JarTemplateSourceUTest/testDirectorySource.jar";

		ITemplateSource templateSource = new JarTemplateSource(
				repositoryPath, templatePath, "subdirectory");

		assert repositoryPath.equals(templateSource.getRepository());
		assert templatePath.equals(templateSource.getTemplate());
		assert "subdirectory".equals(templateSource.getResource());
		assert true == templateSource.exists();
		assert false == templateSource.isFile();
		assert true == templateSource.isDirectory();
		assert 2 == templateSource.listEntries().size();

		ITemplateSourceEntry entry = templateSource.getEntry("/");
		assert null != entry;
		assert templatePath.equals(entry.getTemplate());
		assert "/".equals(entry.getName());
		assert true == entry.exists();
		assert false == entry.isFile();
		assert true == entry.isDirectory();

		entry = templateSource.getEntry("testSourceFile2.txt");
		assert null != entry;
		assert templatePath.equals(entry.getTemplate());
		assert "testSourceFile2.txt".equals(entry.getName());
		assert true == entry.exists();
		assert true == entry.isFile();
		assert false == entry.isDirectory();
	}
}