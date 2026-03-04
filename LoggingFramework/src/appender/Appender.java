package appender;

import core.LogMessage;

public interface Appender {
    void append(LogMessage logMessage);
}
