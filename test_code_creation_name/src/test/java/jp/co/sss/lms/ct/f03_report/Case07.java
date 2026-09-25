package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
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
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		List<WebElement> trList = webDriver.findElements(By.tagName("tr"));
		WebElement trButton = null;
		String title = "セクション詳細 | LMS";

		// 未提出が含まれるtrかを判断し、未提出の詳細ボタンを取得
		for (WebElement tr : trList) {
			if (tr.getText().contains("未提出")) {
				trButton = tr.findElement(By.cssSelector("input.btn"));
				break;
			}
		}

		trButton.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		WebElement reportButton = webDriver.findElement(By.cssSelector("input.btn"));
		String title = "レポート登録 | LMS";

		reportButton.click();
		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// TODO ここに追加
		WebElement inputForm = webDriver.findElement(By.cssSelector("TextArea.form-control"));
		WebElement registButton = webDriver.findElement(By.cssSelector("button.btn-primary"));
		String enterText = "テスト";
		String checkButton = "確認する";

		inputForm.sendKeys(enterText);
		registButton.click();
		getEvidence(new Object() {
		});

		WebElement reportButton = webDriver.findElement(By.cssSelector("input.btn"));
		assertThat(reportButton.getAttribute("value"), is(containsString(checkButton)));
	}
}
