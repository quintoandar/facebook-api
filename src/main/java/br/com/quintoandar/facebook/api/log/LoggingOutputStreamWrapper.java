package br.com.quintoandar.facebook.api.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingOutputStreamWrapper extends OutputStream {

  final Logger logger = Logger.getLogger(LoggingOutputStreamWrapper.class.getName());
  ByteArrayOutputStream myBuffer = new ByteArrayOutputStream();
  private OutputStream target;
  private Level loggingLevel = Level.OFF;

  public LoggingOutputStreamWrapper(OutputStream target) {
    this.target = target;
  }

  @Override
  public void write(int data) throws IOException {
    try {
      myBuffer.write(data);
      target.write(data);
      // When using @FormParam this logs will have to be enabled for debugging
      logger.log(loggingLevel, myBuffer.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void write(byte[] data) {
    try {
      myBuffer.write(data);
      target.write(data);
      // When using @FormParam this logs will have to be enabled for debugging
      logger.log(loggingLevel, myBuffer.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void flush() {
    logger.log(loggingLevel, myBuffer.toString());
    try {
      target.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void close() {
    // This is the standard log to use
    logger.log(loggingLevel, myBuffer.toString());
    try {
      target.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
