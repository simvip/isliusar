package my.jpa.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ivan Sliusar on 07.09.2018.
 * Red Line Soft corp.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestParameter {
    public String key;
    public String value;
}
