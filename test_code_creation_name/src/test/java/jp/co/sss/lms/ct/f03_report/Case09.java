package jp.co.sss.lms.ct.f03_report;

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
import org.openqa.selenium.support.ui.Select;

/**
 * 結合テスト レポート機能
 * ケース09
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース09 受講生 レポート登録 入力チェック")
public class Case09 {

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
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		WebElement userId = webDriver.findElement(By.id("loginId"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement loginButton = webDriver.findElement(By.cssSelector("input.btn-primary"));
		String title = "コース詳細 | LMS";

		userId.clear();
		password.clear();

		// 環境変数を利用して入力
		userId.sendKeys(System.getenv("testLmsUser"));
		password.sendKeys(System.getenv("testLmsPass"));

		loginButton.click();

		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		WebElement userNameLink = webDriver.findElement(By.cssSelector("a small"));
		String title = "ユーザー詳細";

		userNameLink.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		String scrollAmount = "500";
		scrollBy(scrollAmount);
		List<WebElement> trList = webDriver.findElements(By.tagName("tr"));
		WebElement trButton = null;
		String title = "レポート登録 | LMS";

		for (WebElement tr : trList) {
			if (tr.getText().contains("週報")) {
				trButton = tr.findElement(By.cssSelector("input.btn[value^='修正']"));
				break;
			}
		}

		trButton.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		// TODO ここに追加
		String formName = "intFieldName_0";
		WebElement inputForm = webDriver.findElement(By.id(formName));
		String scrollAmount = "100";
		scrollBy(scrollAmount);
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));

		inputForm.clear();
		registButton.click();
		getEvidence(new Object() {
		});

		WebElement errorForm = webDriver.findElement(By.id(formName));
		assertThat(errorForm.getAttribute("class"), is(containsString("errorInput")));
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：理解度が未入力")
	void test06() {
		// TODO ここに追加
		// 前回クリアしたところを取得
		String formName = "intFieldName_0";
		WebElement inputForm = webDriver.findElement(By.id(formName));
		String formName2 = "intFieldValue_0";
		WebElement inputForm2 = webDriver.findElement(By.id(formName2));
		Select selectForm = new Select(inputForm2);
		String scrollAmount = "100";
		scrollBy(scrollAmount);
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));

		// 前回クリアしたところに入力
		inputForm.sendKeys("ITリテラシー①");
		selectForm.selectByIndex(0);
		registButton.click();
		getEvidence(new Object() {
		});

		WebElement errorForm = webDriver.findElement(By.id(formName2));
		assertThat(errorForm.getAttribute("class"), is(containsString("errorInput")));
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が数値以外")
	void test07() {
		// TODO ここに追加
		// 前回クリアしたところを取得
		String formName = "intFieldValue_0";
		WebElement inputForm = webDriver.findElement(By.id(formName));
		String formName2 = "content_0";
		Select selectForm = new Select(inputForm);
		WebElement inputForm2 = webDriver.findElement(By.id(formName2));
		String scrollAmount = "100";
		scrollBy(scrollAmount);
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));

		// 前回クリアしたところに入力
		selectForm.selectByIndex(2);
		inputForm2.clear();
		inputForm2.sendKeys("テスト");
		registButton.click();
		getEvidence(new Object() {
		});

		WebElement errorForm = webDriver.findElement(By.id(formName2));
		assertThat(errorForm.getAttribute("class"), is(containsString("errorInput")));
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が範囲外")
	void test08() {
		// TODO ここに追加
		String formName = "content_0";
		WebElement inputForm = webDriver.findElement(By.id(formName));
		String scrollAmount = "100";
		scrollBy(scrollAmount);
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));

		inputForm.clear();
		inputForm.sendKeys("0");
		registButton.click();
		getEvidence(new Object() {
		});

		WebElement errorForm = webDriver.findElement(By.id(formName));
		assertThat(errorForm.getAttribute("class"), is(containsString("errorInput")));
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度・所感が未入力")
	void test09() {
		// TODO ここに追加
		String formName = "content_0";
		String formName2 = "content_1";
		WebElement inputForm = webDriver.findElement(By.id(formName));
		WebElement inputForm2 = webDriver.findElement(By.id(formName2));
		String scrollAmount = "100";
		scrollBy(scrollAmount);
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));

		inputForm.clear();
		inputForm2.clear();
		registButton.click();
		getEvidence(new Object() {
		});

		WebElement errorForm = webDriver.findElement(By.id(formName));
		WebElement errorForm2 = webDriver.findElement(By.id(formName2));
		assertThat(errorForm.getAttribute("class"), is(containsString("errorInput")));
		assertThat(errorForm2.getAttribute("class"), is(containsString("errorInput")));
	}

	@Test
	@Order(10)
	@DisplayName("テスト10 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：所感・一週間の振り返りが2000文字超")
	void test10() {
		// TODO ここに追加
		String formName = "content_0";
		String formName2 = "content_1";
		String formName3 = "content_2";
		WebElement inputForm = webDriver.findElement(By.id(formName));
		WebElement inputForm2 = webDriver.findElement(By.id(formName2));
		WebElement inputForm3 = webDriver.findElement(By.id(formName3));
		String scrollAmount = "100";
		scrollBy(scrollAmount);
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));
		// 100文字のデータ
		String hundredTestString = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

		inputForm.sendKeys("5");
		inputForm3.clear();

		// 100文字を20回送って2000文字にする
		for (int i = 0; i < 20; i++) {
			inputForm2.sendKeys(hundredTestString);
			inputForm3.sendKeys(hundredTestString);
		}

		// 各1文字足すことによって、2001文字にする
		inputForm2.sendKeys("1");
		inputForm3.sendKeys("1");

		registButton.click();
		getEvidence(new Object() {
		});

		WebElement errorForm = webDriver.findElement(By.id(formName2));
		WebElement errorForm2 = webDriver.findElement(By.id(formName3));
		assertThat(errorForm.getAttribute("class"), is(containsString("errorInput")));
		assertThat(errorForm2.getAttribute("class"), is(containsString("errorInput")));
	}

}
