package com.app.gadgetblitz.model.phone.components;

public record Battery(
        Integer capacity__mAh,
        Integer continuous_audio_playtime__h,
        Integer continuous_video_playtime__h,
        String technology
        ) {}
