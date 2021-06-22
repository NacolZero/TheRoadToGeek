package com.nacol.TheRoadToGeek.week_06.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BatchDTO {

    public static String DATA_SOURCE_HIKRI = "hikiri";
    public static String DATA_SOURCE_DRUID = "druid";

    private String dataSourece;

    private String message;

    private List<String> messageList;

    public void useHikri() {
        this.dataSourece = DATA_SOURCE_HIKRI;
    }

    public void useDruid() {
        this.dataSourece = DATA_SOURCE_DRUID;
    }

}
