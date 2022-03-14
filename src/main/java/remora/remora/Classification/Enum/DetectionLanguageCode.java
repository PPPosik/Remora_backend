package remora.remora.Classification.Enum;

import java.util.Optional;

public enum DetectionLanguageCode {
    KO("ko"), // 한국어
    JA("ja"), // 일본어
    ZHCN("zh-CN"), // 중국어 간체
    ZHTW("zh-TW"), // 중국어 번체
    HI("hi"), // 힌디어
    EN("en"), // 영어
    ES("es"), // 스페인어
    FR("fr"), // 프랑스어
    DE("de"), // 독일어
    PT("pt"), // 포르투갈어
    VI("vi"), // 베트남어
    ID("id"), // 인도네시아어
    FA("fa"), // 페르시아어
    AR("ar"), // 아랍어
    MM("mm"), // 미얀마어
    TH("th"), // 태국어
    RU("ru"), // 러시아어
    IT("it"), // 이탈리아어
    UNK("unk") // 알 수 없음
    ;

    private final String value;

    DetectionLanguageCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<DetectionLanguageCode> of(String str) {
        for (DetectionLanguageCode lang : values()) {
            if (lang.getValue().equals(str)) {
                return Optional.of(lang);
            }
        }

        return Optional.empty();
    }
}
