package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		WebElement menuLink = webDriver.findElement(By.cssSelector("a.dropdown-toggle"));

		menuLink.click();

		WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));
		String title = "ヘルプ | LMS";

		helpLink.click();

		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		WebElement questionLink = webDriver.findElement(By.linkText("よくある質問"));
		String title = "よくある質問 | LMS";
		int waitTime = 10;

		questionLink.click();

		// 別タブが開かれるのでそれらに対応するためのハンドル
		Object[] windowHandles = webDriver.getWindowHandles().toArray();
		// おそらく1は二つ目のタブに遷移する
		webDriver.switchTo().window((String) windowHandles[1]);

		visibilityTimeout(By.tagName("h2"), waitTime);

		getEvidence(new Object() {
		});

		assertEquals(title, webDriver.getTitle());
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// TODO ここに追加
		WebElement categoryLink = webDriver.findElement(By.linkText("【研修関係】"));
		String[] checkStrings = { "キャンセル料・途中退校について", "研修の申し込みはどのようにすれば良いですか？" };

		categoryLink.click();

		getEvidence(new Object() {
		});

		List<WebElement> searchResults = webDriver.findElements(By.cssSelector("tr dt.mb10 span:not(.mr10)"));

		// カテゴリ検索で表示された内容がDB上のカテゴリと一致しているか
		for (int i = 0; i < searchResults.size(); i++) {
			assertThat(searchResults.get(i).getText(), is(containsString(checkStrings[i])));
		}
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// TODO ここに追加
		WebElement question = webDriver.findElement(By.cssSelector("dt.mb10"));

		question.click();

		WebElement answer = webDriver.findElement(By.cssSelector("dd[id^='answer-h']"));
		getEvidence(new Object() {
		});

		// 表示されているかを確認
		assertTrue(answer.isDisplayed());
	}

}
