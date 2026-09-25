package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
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
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		List<WebElement> trList = webDriver.findElements(By.tagName("tr"));
		// 週報が実装されているのは2番目だけなので２番目を取り出す。
		WebElement trButton = trList.get(1).findElement(By.cssSelector("input.btn"));
		String title = "セクション詳細 | LMS";

		trButton.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		WebElement reportButton = webDriver.findElement(By.cssSelector("input.btn[value^='提出済み週報']"));
		String title = "レポート登録 | LMS";

		reportButton.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加
		WebElement inputForm = webDriver.findElement(By.id("content_1"));
		String scrollAmount = "100";
		scrollBy(scrollAmount);
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));
		String enterText = "Case08テスト";
		String title = "セクション詳細 | LMS";

		inputForm.clear();
		inputForm.sendKeys(enterText);
		registButton.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
		WebElement userNameLink = webDriver.findElement(By.cssSelector("a small"));
		String title = "ユーザー詳細";

		userNameLink.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加
		String scrollAmount = "500";
		scrollBy(scrollAmount);
		List<WebElement> trList = webDriver.findElements(By.tagName("tr"));
		WebElement trButton = null;
		String reportString = "Case08テスト";

		for (WebElement tr : trList) {
			if (tr.getText().contains("週報")) {
				trButton = tr.findElement(By.cssSelector("input.btn[value='詳細']"));
				break;
			}
		}

		trButton.click();

		List<WebElement> reportTests = webDriver.findElements(By.cssSelector("th.wq + td"));
		WebElement reportTest = reportTests.get(2);
		getEvidence(new Object() {
		});

		assertEquals(reportString, reportTest.getText());
	}

}
