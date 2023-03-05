package com.minhapresenca.minhapresencabackend.View;

import com.minhapresenca.minhapresencabackend.entity.Presence;
import lombok.Builder;

import java.util.List;

@Builder
public record PresenceView(String nomeAluno,List<Presence> presenceList) {}
