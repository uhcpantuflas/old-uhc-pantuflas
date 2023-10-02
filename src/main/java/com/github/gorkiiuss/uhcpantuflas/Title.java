package com.github.gorkiiuss.uhcpantuflas;

public class Title {
    private final String title;
    private final String subtitle;
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public Title formatAsJoining() {
        String joiningTitle = new MinecraftTextBuilder()
                .addText("xxX ").strikethrough().obfuscated()
                .addText(title).bold()
                .addText(" Xxx").strikethrough().obfuscated()
                .colorAll(MinecraftColor.YELLOW).build();
        String joiningSubtitle = new MinecraftTextBuilder().addText(subtitle).color(MinecraftColor.GRAY).build();

        return new Title(joiningTitle, joiningSubtitle, fadeIn, stay, fadeOut);
    }
}
