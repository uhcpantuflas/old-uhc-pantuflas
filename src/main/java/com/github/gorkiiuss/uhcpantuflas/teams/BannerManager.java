package com.github.gorkiiuss.uhcpantuflas.teams;

import com.github.gorkiiuss.uhcpantuflas.UHCBanner;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

public class BannerManager {
    private static BannerManager instance;
    private final UHCBanner[] banners = new UHCBanner[] {
            new UHCBanner(DyeColor.PURPLE, new Pattern[]{
                    new Pattern(DyeColor.MAGENTA, PatternType.STRIPE_SMALL),
                    new Pattern(DyeColor.PURPLE, PatternType.BRICKS),
                    new Pattern(DyeColor.MAGENTA, PatternType.CURLY_BORDER),
                    new Pattern(DyeColor.BLACK, PatternType.BORDER)
            }),
            new UHCBanner(DyeColor.LIME, new Pattern[]{
                    new Pattern(DyeColor.BLACK, PatternType.STRIPE_SMALL),
                    new Pattern(DyeColor.BLACK, PatternType.CROSS),
                    new Pattern(DyeColor.GREEN, PatternType.BORDER),
                    new Pattern(DyeColor.WHITE, PatternType.CIRCLE_MIDDLE),
                    new Pattern(DyeColor.BLACK, PatternType.CURLY_BORDER),
                    new Pattern(DyeColor.BLACK, PatternType.SKULL)
            }),
            new UHCBanner(DyeColor.BLACK, new Pattern[]{
                    new Pattern(DyeColor.PURPLE, PatternType.FLOWER),
                    new Pattern(DyeColor.RED, PatternType.STRIPE_SMALL),
                    new Pattern(DyeColor.BLACK, PatternType.CURLY_BORDER),
                    new Pattern(DyeColor.BLACK, PatternType.TRIANGLE_TOP),
                    new Pattern(DyeColor.RED, PatternType.SKULL),
                    new Pattern(DyeColor.BLACK, PatternType.CREEPER),
            }),
            new UHCBanner(DyeColor.YELLOW, new Pattern[]{
                    new Pattern(DyeColor.BLACK, PatternType.CURLY_BORDER),
                    new Pattern(DyeColor.ORANGE, PatternType.GRADIENT),
                    new Pattern(DyeColor.BLACK, PatternType.CIRCLE_MIDDLE),
                    new Pattern(DyeColor.BLACK, PatternType.CROSS),
                    new Pattern(DyeColor.ORANGE, PatternType.FLOWER),
                    new Pattern(DyeColor.BLACK, PatternType.TRIANGLE_TOP),
            }),
            new UHCBanner(DyeColor.WHITE,  new Pattern[]{
                    new Pattern(DyeColor.BLACK, PatternType.CURLY_BORDER),
                    new Pattern(DyeColor.BLACK, PatternType.FLOWER),
                    new Pattern(DyeColor.BLACK, PatternType.CIRCLE_MIDDLE),
                    new Pattern(DyeColor.BLACK, PatternType.CROSS),
                    new Pattern(DyeColor.BLACK, PatternType.STRAIGHT_CROSS),
                    new Pattern(DyeColor.WHITE, PatternType.FLOWER),
            }),
            new UHCBanner(DyeColor.LIGHT_BLUE, new Pattern[]{
                    new Pattern(DyeColor.PURPLE, PatternType.GRADIENT_UP),
                    new Pattern(DyeColor.BLACK, PatternType.STRIPE_SMALL),
                    new Pattern(DyeColor.BLACK, PatternType.CURLY_BORDER),
                    new Pattern(DyeColor.WHITE, PatternType.FLOWER),
                    new Pattern(DyeColor.BLACK, PatternType.FLOWER),
            }),
            new UHCBanner(DyeColor.PINK, new Pattern[]{
                    new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT),
                    new Pattern(DyeColor.PINK, PatternType.RHOMBUS_MIDDLE),
                    new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT),
                    new Pattern(DyeColor.BLACK, PatternType.CIRCLE_MIDDLE),
                    new Pattern(DyeColor.BLACK, PatternType.CURLY_BORDER),
            })
    };

    private BannerManager() {

    }

    public static synchronized BannerManager get() {
        if (instance == null) instance = new BannerManager();
        return instance;
    }

    public UHCBanner getBanner(int bannerIdx) {
        if (bannerIdx >= banners.length) {
            return new UHCBanner(DyeColor.WHITE, new Pattern[0]);
        }

        return banners[bannerIdx];
    }
}
