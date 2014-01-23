package org.gold.miner.tchoutchou.communication;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.communication.ComServeur;
import org.junit.Test;

public class ComServeurTest {

	@Test
	public void listener_should_get_message_on_stdout() throws Exception {
		// Given
		String expectedMessage = "TEST MESSAGE";
		
		OutputStreamWriter outWriter = new OutputStreamWriter(System.out);
		InputStream in = new ByteArrayInputStream(expectedMessage.getBytes());
		System.setIn(in);
		
		ComServeur listener = new ComServeur(in);
		
		// When
		outWriter.write(expectedMessage);
		
		// Then
		String result = listener.getNextLine();
		Assertions.assertThat(result).isEqualTo(expectedMessage);
	}

}
