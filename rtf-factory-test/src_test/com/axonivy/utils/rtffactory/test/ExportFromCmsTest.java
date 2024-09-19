package com.axonivy.utils.rtffactory.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.*;

import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectChildAccessor;
import ch.ivyteam.ivy.cm.ContentObjectReader;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.cm.ContentObjectValueAccessor;
import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import com.axonivy.utils.rtffactory.ExportFromCms;

@IvyTest
public class ExportFromCmsTest {

	private final String cmsUri = "/path/to/resource";
	private final String ext = "txt";
	private File tempDir;

	@BeforeEach
	void setUp() throws IOException {
		tempDir = new File(System.getProperty("java.io.tmpdir"), "test-export");
		tempDir.mkdirs();
	}

	@Test
	void testExportSuccess() throws Exception {
		// Mocked objects
		File tempFileMock = mock(File.class);
		InputStream inputStreamMock = mock(InputStream.class);
		FileOutputStream fosMock = mock(FileOutputStream.class);
		MockedStatic<StringUtils> mockedStringUtils = mockStatic(StringUtils.class);
		MockedStatic<IOUtils> mockedIOUtils = mockStatic(IOUtils.class);

		// Mock StringUtils.removeStart behavior
		mockedStringUtils.when(() -> StringUtils.removeStart(cmsUri, "/")).thenReturn("path/to/resource");

		// Mock the temp file
		when(tempFileMock.getParentFile()).thenReturn(tempDir);
		when(tempFileMock.exists()).thenReturn(false);
		when(tempFileMock.createNewFile()).thenReturn(true);

		// Mock the behavior for directory creation
		tempFileMock.getParentFile().mkdirs();

		// Mock CMS object behavior
		ContentObjectValue covMock = mock(ContentObjectValue.class); // Mock the CmsObject type

		// Mock Ivy CMS interactions (depending on your Ivy CMS framework)
		MockedStatic<Ivy> ivyStaticMock = mockStatic(Ivy.class);

		IContentManagementSystem cmsMock = mock(IContentManagementSystem.class);
		ContentObject mockContentObject = mock(ContentObject.class);
		ContentObjectChildAccessor mockContentObjectChildAccessor = mock(ContentObjectChildAccessor.class);
		ContentObjectValueAccessor mockContentObjectValueAccessor = mock(ContentObjectValueAccessor.class);

		ivyStaticMock.when(() -> Ivy.cms()).thenReturn(cmsMock);
		when(cmsMock.root()).thenReturn(mockContentObject);
		when(mockContentObject.child()).thenReturn(mockContentObjectChildAccessor);
		when(mockContentObjectChildAccessor.file(anyString(), anyString())).thenReturn(mockContentObject);
		when(mockContentObject.value()).thenReturn(mockContentObjectValueAccessor);
		when(mockContentObjectValueAccessor.get()).thenReturn(covMock);

		ContentObjectReader corMock = mock(ContentObjectReader.class);
		when(covMock.read()).thenReturn(corMock);
		when(corMock.inputStream()).thenReturn(inputStreamMock);

		// Mock IOUtils.copy behavior
		mockedIOUtils.when(() -> IOUtils.copy(inputStreamMock, fosMock)).thenReturn(1024);

		// Call the method under test
		File result = ExportFromCms.export(cmsUri, ext);

		// Assertions
		assertNotNull(result);
		assertTrue(result.exists());

		// Verify mocks
		mockedStringUtils.verify(() -> StringUtils.removeStart(cmsUri, "/"));

		// Clean up mocks
		mockedStringUtils.close();
		ivyStaticMock.close();
		mockedIOUtils.close();
	}

}
