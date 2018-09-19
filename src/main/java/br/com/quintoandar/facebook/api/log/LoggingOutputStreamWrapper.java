package br.com.quintoandar.facebook.api.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingOutputStreamWrapper extends OutputStream {

  ByteArrayOutputStream myBuffer = new ByteArrayOutputStream();
  private OutputStream target;

  public LoggingOutputStreamWrapper(OutputStream target) {
    this.target = target;
  }

  @Override
  public void write(int data) throws IOException {
    try {
      myBuffer.write(data);
      target.write(data);
      // When using @FormParam this logs will have to be enabled for debugging
      log.debug(myBuffer.toString());
    } catch (IOException e) {
      log.error("Could not write Facebook request data to internal buffer!", e);
    }
  }

  @Override
  public void write(byte[] data) {
    try {
      myBuffer.write(data);
      target.write(data);
      // When using @FormParam this logs will have to be enabled for debugging
      log.debug(myBuffer.toString());
    } catch (IOException e) {
      log.error("Could not write Facebook request data to internal buffer!", e);
    }
  }

  @Override
  public void flush() {
    log.debug(myBuffer.toString());
    try {
      target.flush();
      myBuffer.flush();
    } catch (IOException e) {
      log.error("Could not flush internal buffer as Facebook request!", e);
    }
  }

  @Override
  public void close() {
    // This is the standard log to use
    log.debug(myBuffer.toString());
    try {
      target.close();
      myBuffer.close();
    } catch (IOException e) {
      log.error("Could not close internal buffer as Facebook request!", e);
    }
  }
}
