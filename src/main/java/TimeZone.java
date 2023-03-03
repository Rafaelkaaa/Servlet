import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.net.URLEncoder.encode;

public class TimeZone {
    @Getter
    @Setter
    private int timeZone;
    @Getter
    @Setter
    private String timeZoneCode;
    @Getter
    @Setter
    private String timeZoneParameter;
    public TimeZone(String timeZoneParameter) {
        this.timeZoneParameter = timeZoneParameter;
    }

    String getTimeWithTimeZone() {
        convertTimeZoneParameterToInt();
        setTimeZoneCode();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        Clock clock = Clock.offset
                (Clock.system(ZoneId.of("UTC")),
                        Duration.ofHours(timeZone));
        return dateTimeFormat.format(LocalDateTime.now(clock)) + " " + timeZoneCode;
    }


    void convertTimeZoneParameterToInt() {
        if (timeZoneParameter != null) {
            String timeZoneString = encode(timeZoneParameter)
                    .toLowerCase()
                    .replace("utc", "")
                    .replace("+", "");
            setTimeZone(parseInt(timeZoneString));
            return;
        }
        setTimeZone(0);
    }

    private Integer parseInt(String timeZoneString) {
        try {
            return Integer.parseInt(timeZoneString);
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    void setTimeZoneCode() {
        if (getTimeZone() >= 0) {
            timeZoneCode = "UTC+" + getTimeZone();
        }else {
            timeZoneCode = "UTC" + getTimeZone();
        }
    }
}