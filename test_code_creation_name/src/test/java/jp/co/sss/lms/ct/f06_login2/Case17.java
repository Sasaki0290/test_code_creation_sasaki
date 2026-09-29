package jp.co.sss.lms.ct.f06_login2;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

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
 * ケース17
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース17 受講生 初回ログイン 正常系")
public class Case17 {

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
	@DisplayName("テスト04 変更パスワードを入力し「変更」ボタン押下")
	void test04() {
		// TODO ここに追加
		WebElement chengeButton = webDriver.findElement(By.xpath("//button[text()='変更']"));
		WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		String title = "コース詳細 | LMS";

		currentPassword.sendKeys(System.getenv("testFirstLmsUserAndPass"));
		password.sendKeys(System.getenv("testLmsPass"));
		passwordConfirm.sendKeys(System.getenv("testLmsPass"));

		chengeButton.click();

		WebElement chengeButton2 = webDriver.findElement(By.id("upd-btn"));
		chengeButton2.click();

		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

}
