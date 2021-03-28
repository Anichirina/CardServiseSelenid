import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class testSelenideServise {
//правильное заполнение
    @Test
    void shouldSubitRequest() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Сергей");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(byText("Продолжить")).click();
        $(".paragraph").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    //Английские символы в фио
    @Test
    void shouldSubitNegativRequestEnglishName() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Sergey");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    //Некорректные символы в фио
    @Test
    void shouldInccorectSimvolName() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Серге++");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    //Некорректные символы в фио
    @Test
    void shouldEmptyFieldsName() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text  .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    ///Лищние символы в номере телефона
    @Test
    void shouldExcessNumbersInncorectPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Сергей");
        $("[data-test-id=phone] input").setValue("+792700000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel  .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    ///Меньше символов в номере телефона
    @Test
    void shouldLessNumbersInncorectPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Сергей");
        $("[data-test-id=phone] input").setValue("+79270000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel  .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    ///нет плюса в номере телефона
    @Test
    void shouldNoPlusPhoneNumber() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Сергей");
        $("[data-test-id=phone] input").setValue("79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel  .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    ///нет плюса в номере телефона
    @Test
    void shouldEmptyFieldsPhoneNumber() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Сергей");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel  .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }
    ///посторонние символы в номере телефона
    @Test
    void shouldInncorectSimbolInPhoneNumber() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("Сергей");
        $("[data-test-id=phone] input").setValue("+7911-gh11111");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel  .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    //отправка пустой страницы
    @Test
    void shouldSendEmptyFieldsPages() {
        open("http://localhost:9999/");
        SelenideElement form = $("[data-test-id=callback-form]");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[type=button]").click();
        $(".input_type_text  .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }
}

