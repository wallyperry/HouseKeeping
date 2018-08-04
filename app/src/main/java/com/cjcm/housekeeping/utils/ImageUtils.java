package com.cjcm.housekeeping.utils;

import com.cjcm.housekeeping.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/7/23
 */
public class ImageUtils {

    public static List<String> imageErgodic(String s) {
        List<String> imageList = new ArrayList<>();
        String images = s;
        while (images.length() > 4) {
            String img = images.substring(0, images.indexOf(".") + 4);
            images = images.substring(img.length());
            imageList.add(Constants.Api.BASE_URL + img.replaceAll("\\|", ""));
        }
        return imageList;
    }
}
