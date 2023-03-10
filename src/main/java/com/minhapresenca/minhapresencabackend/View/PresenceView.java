package com.minhapresenca.minhapresencabackend.View;

import com.minhapresenca.minhapresencabackend.entity.Presence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceView {
    String nameStudent;
    List<Presence> presenceList;

}
