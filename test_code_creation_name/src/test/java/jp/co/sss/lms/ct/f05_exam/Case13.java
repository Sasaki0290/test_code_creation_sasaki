//package jp.co.sss.lms.ct.f05_exam;
//
//import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//
///**
// * 結合テスト 試験実施機能
// * ケース13
// * @author holy
// */
//@TestMethodOrder(OrderAnnotation.class)
//@DisplayName("ケース13 受講生 試験の実施 結果0点")
//public class Case13 {
//
//	/** テスト07およびテスト08 試験実施日時 */
//	static Date date;
//
//	/** 前処理 */
//	@BeforeAll
//	static void before() {
//		createDriver();
//	}
//
//	/** 後処理 */
//	@AfterAll
//	static void after() {
//		closeDriver();
//	}
//
//	@Test
//	@Order(1)
//	@DisplayName("テスト01 トップページURLでアクセス")
//	void test01() {
//		// TODO ここに追加
//		String url = "http://localhost:8080/lms/";
//		String title = "ログイン | LMS";
//
//		// トップページへ遷移
//		goTo(url);
//
//		// エビデンスを取得
//		getEvidence(new Object() {
//		});
//
//		assertEquals(title, webDriver.getTitle());
//	}
//
//	@Test
//	@Order(2)
//	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
//	void test02() {
//		// TODO ここに追加
//		WebElement userId = webDriver.findElement(By.id("loginId"));
//		WebElement password = webDriver.findElement(By.id("password"));
//		WebElement loginButton = webDriver.findElement(By.cssSelector("input.btn-primary"));
//		String title = "コース詳細 | LMS";
//
//		userId.clear();
//		password.clear();
//
//		// 環境変数を利用して入力
//		userId.sendKeys(System.getenv("testLmsUser"));
//		password.sendKeys(System.getenv("testLmsPass"));
//
//		loginButton.click();
//
//		getEvidence(new Object() {
//		});
//
//		assertEquals(title, webDriver.getTitle());
//	}
//
//	@Test
//	@Order(3)
//	@DisplayName("テスト03 「試験有」の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
//	void test03() {
//		// TODO ここに追加
//		List<WebElement> trList = webDriver.findElements(By.tagName("tr"));
//		// 試験が実装されているのは2番目だけなので２番目を取り出す。
//		WebElement trButton = trList.get(1).findElement(By.cssSelector("input.btn"));
//		String title = "セクション詳細 | LMS";
//
//		trButton.click();
//		getEvidence(new Object() {
//		});
//
//		assertEquals(title, webDriver.getTitle());
//	}
//
//	@Test
//	@Order(4)
//	@DisplayName("テスト04 「本日の試験」エリアの「詳細」ボタンを押下し試験開始画面に遷移")
//	void test04() {
//		// TODO ここに追加
//		WebElement detailButton = webDriver.findElement(By.cssSelector("input.btn[value='詳細']"));
//
//		detailButton.click();
//
//		String title = "試験【" + webDriver.findElement(By.cssSelector("div#main h2")).getText() + "】 | LMS";
//		getEvidence(new Object() {
//		});
//
//		assertEquals(title, webDriver.getTitle());
//	}
//
//	@Test
//	@Order(5)
//	@DisplayName("テスト05 「試験を開始する」ボタンを押下し試験問題画面に遷移")
//	void test05() {
//		// TODO ここに追加
//		WebElement startButton = webDriver.findElement(By.cssSelector("input.btn[value^='試験']"));
//		startButton.click();
//
//		String title = webDriver.findElement(By.cssSelector("div#main h2")).getText() + " | LMS";
//		getEvidence(new Object() {
//		});
//
//		assertEquals(title, webDriver.getTitle());
//	}
//
//	@Test
//	@Order(6)
//	@DisplayName("テスト06 未回答の状態で「確認画面へ進む」ボタンを押下し試験回答確認画面に遷移")
//	void test06() {
//		// TODO ここに追加
//		scrollBy("5000");
//		WebElement endButton = webDriver.findElement(By.cssSelector("input.btn[value^='確認画面']"));
//		endButton.click();
//
//		String checkText = "あなたの回答";
//		getEvidence(new Object() {
//		});
//
//		assertEquals(checkText, webDriver.findElement(By.cssSelector("div.panel h6")).getText());
//	}
//
//	@Test
//	@Order(7)
//	@DisplayName("テスト07 「回答を送信する」ボタンを押下し試験結果画面に遷移")
//	void test07() throws InterruptedException {
//		// TODO ここに追加
//		scrollBy("5000");
//		WebElement sendButton = webDriver.findElement(By.cssSelector("button#sendButton"));
//		sendButton.click();
//
//		Alert alert = webDriver.switchTo().alert();
//		alert.accept();
//
//		String checkText = "あなたのスコア";
//		getEvidence(new Object() {
//		});
//
//		assertThat(webDriver.findElement(By.cssSelector("div#examBeing small")).getText(),
//				is(containsString(checkText)));
//	}
//
//	@Test
//	@Order(8)
//	@DisplayName("テスト08 「戻る」ボタンを押下し試験開始画面に遷移後当該試験の結果が反映される")
//	void test08() {
//		// TODO ここに追加
//		try {
//		} catch (Exception e) {
//			System.err.println(e);
//		} finally {
//			scrollBy("5000");
//			WebElement backButton = webDriver.findElement(By.cssSelector("input.btn[value^='戻る']"));
//			backButton.click();
//
//			getEvidence(new Object() {
//			});
//		}
//	}
//
//}
