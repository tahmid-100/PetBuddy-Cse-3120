package com.example.petbuddy;

import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainActivityHelperTest {

    private MainActivityHelper mainActivityHelper;
    private ImageView mockImageView;
    private TextView mockTextView;

    @Before
    public void setUp() {
        mockImageView = Mockito.mock(ImageView.class);
        mockTextView = Mockito.mock(TextView.class);

        mainActivityHelper = new MainActivityHelper(mockImageView, mockTextView);
    }

    @Test
    public void testSetImageResource() {
        int resId = 123;
        mainActivityHelper.setImageResource(resId);

        Mockito.verify(mockImageView).setImageResource(resId);
    }

    @Test
    public void testGetImageResource() {
        int resId = 123;
        Mockito.when(mockImageView.getTag()).thenReturn(resId);

        int result = mainActivityHelper.getImageResource();
        assertEquals(resId, result);
    }

    @Test
    public void testSetSloganText() {
        String text = "Hello World";
        mainActivityHelper.setSloganText(text);

        Mockito.verify(mockTextView).setText(text);
    }

    @Test
    public void testGetSloganText() {
        String text = "Hello World";
        Mockito.when(mockTextView.getText()).thenReturn(text);

        String result = mainActivityHelper.getSloganText();
        assertEquals(text, result);
    }
}

