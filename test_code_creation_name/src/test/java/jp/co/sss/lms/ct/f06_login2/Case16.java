package jp.co.sss.lms.ct.f06_login2;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能②
 * ケース16
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース16 受講生 初回ログイン 変更パスワード未入力")
public class Case16 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		String url = "http://localhost:8080/lms/";
		String title = "ログイン | LMS";

		// トップページへ遷移
		goTo(url);

		// エビデンスを取得
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		WebElement userId = webDriver.findElement(By.id("loginId"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement loginButton = webDriver.findElement(By.cssSelector("input.btn-primary"));
		String title = "セキュリティ規約 | LMS";

		userId.clear();
		password.clear();

		// 環境変数を利用して入力
		userId.sendKeys(System.getenv("testFirstLmsUserAndPass"));
		password.sendKeys(System.getenv("testFirstLmsUserAndPass"));

		loginButton.click();

		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() {
		// TODO ここに追加
		WebElement checkbox = webDriver.findElement(By.cssSelector("div.checkbox input"));
		WebElement agreeButton = webDriver.findElement(By.cssSelector("button.btn-primary"));
		String title = "パスワード変更 | LMS";

		checkbox.click();
		agreeButton.click();

		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 パスワードを未入力で「変更」ボタン押下")
	void test04() {
		// TODO ここに追加
		WebElement chengeButton = webDriver.findElement(By.xpath("//button[text()='変更']"));
		chengeButton.click();

		visibilityTimeout(By.id("upd-btn"), 5);

		WebElement chengeButton2 = webDriver.findElement(By.id("upd-btn"));
		chengeButton2.click();

		List<WebElement> errorList = webDriver.findElements(By.tagName("span.error"));

		for (WebElement error : errorList) {
			assertThat(error.getText(), is(containsString("必須です")));
		}

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 20文字以上の変更パスワードを入力し「変更」ボタン押下")
	void test05() {
		// TODO ここに追加
		WebElement chengeButton = webDriver.findElement(By.xpath("//button[text()='変更']"));
		WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		String testPass = "Pass123456Word1234567";

		currentPassword.sendKeys(System.getenv("testFirstLmsUserAndPass"));
		password.sendKeys(testPass);
		passwordConfirm.sendKeys(testPass);

		chengeButton.click();

		visibilityTimeout(By.id("upd-btn"), 5);

		WebElement chengeButton2 = webDriver.findElement(By.id("upd-btn"));
		chengeButton2.click();

		List<WebElement> errorList = webDriver.findElements(By.tagName("span.error"));

		for (WebElement error : errorList) {
			assertThat(error.getText(),
					is(containsString("8～20文字")));
		}
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 ポリシーに合わない変更パスワードを入力し「変更」ボタン押下")
	void test06() {
		// TODO ここに追加 
		WebElement chengeButton = webDriver.findElement(By.xpath("//button[text()='変更']"));
		WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		String testPass = "password";

		currentPassword.sendKeys(System.getenv("testFirstLmsUserAndPass"));
		password.sendKeys(testPass);
		passwordConfirm.sendKeys(testPass);
		chengeButton.click();

		visibilityTimeout(By.id("upd-btn"), 5);

		WebElement chengeButton2 = webDriver.findElement(By.id("upd-btn"));
		chengeButton2.click();

		List<WebElement> errorList = webDriver.findElements(By.tagName("span.error"));

		for (WebElement error : errorList) {
			assertThat(error.getText(), is(containsString("半角英数字のみ使用可能です。また、半角英大文字、半角英小文字、数字を含めた8～20文字")));
		}
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 一致しない確認パスワードを入力し「変更」ボタン押下")
	void test07() {
		// TODO ここに追加
		WebElement chengeButton = webDriver.findElement(By.xpath("//button[text()='変更']"));
		WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		String testPass = "Password456";
		String testPass2 = "passWord321";

		currentPassword.sendKeys(System.getenv("testFirstLmsUserAndPass"));
		password.sendKeys(testPass);
		passwordConfirm.sendKeys(testPass2);
		chengeButton.click();

		visibilityTimeout(By.id("upd-btn"), 5);

		WebElement chengeButton2 = webDriver.findElement(By.id("upd-btn"));
		chengeButton2.click();

		List<WebElement> errorList = webDriver.findElements(By.tagName("span.error"));

		for (WebElement error : errorList) {
			assertThat(error.getText(), is(containsString("パスワードと確認パスワードが一致しません")));
		}
	}

}
