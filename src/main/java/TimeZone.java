import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.net.URLEncoder.encode;

public class TimeZone {
    @Setter
    @Getter
    private int timeZone;

    @Getter
    @Setter
    private String timeZoneCode;


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
        return dateTimeFormat.format(LocalDateTime.now(clock)) + "&nbsp" + timeZoneCode;
    }

    void convertTimeZoneParameterToInt() {
        if (timeZoneParameter != null) {
            String timeZone = encode(timeZoneParameter)
                    .toLowerCase()
                    .replace("utc", "")
                    .replace("+", "");

            try {
                setTimeZone(Integer.parseInt(timeZone));
                return;
            } catch (IllegalArgumentException ex) {
                setTimeZone(0);
                return;
            }
        }
        setTimeZone(0);
    }

    void setTimeZoneCode() {
        if (getTimeZone() >= 0) {
            timeZoneCode = "UTC+" + getTimeZone();
        }else {
            timeZoneCode = "UTC" + getTimeZone();
        }
    }
}