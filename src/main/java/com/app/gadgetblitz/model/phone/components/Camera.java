package com.app.gadgetblitz.model.phone.components;

public record Camera(
        Integer camera_back__mp,
        Integer camera_front__mp,
        Integer camera_back_amount,
        Integer camera_front_amount,
        Integer camera_back_digital_zoom,
        Integer camera_back_max_frame_rate__fps,
        Integer camera_back_number_of_lens_elements,
        Boolean camera_back_has_flash,
        Boolean camera_back_has_panorama,
        Boolean camera_back_has_auto_focus,
        Boolean camera_back_has_geotagging,
        Boolean camera_back_has_night_mode,
        Boolean camera_back_has_time_lapse_modus,
        Boolean camera_back_has_video_stabilizer,
        String camera_back_resolution,
        String camera_back_type_flash
) {}
