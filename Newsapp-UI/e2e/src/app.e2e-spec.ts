import { AppPage } from './app.po';
import { browser,by,element } from 'protractor';
import { protractor } from 'protractor/built/ptor';

describe('News Application end to end testing', () => {
  let page: AppPage;
  browser.driver.manage().window().maximize();   
  beforeEach(() => {    
    page = new AppPage();
    
  });

  it('should display Title', () => {   
    page.navigateTo();    
    expect(browser.getTitle()).toEqual('THE DAILY');    
  });
  
  it('should be redirect to /login route on opening the application', () => {   
    expect(browser.getCurrentUrl()).toContain('/login');   
  });
  
  it('should be redirect to /register route', () => {
    browser.element(by.id('register-btn-id')).click();
    expect(browser.getCurrentUrl()).toContain('/register');    
  });
  
  it('should be able to register user', () => {
    browser.element(by.id('firstname')).sendKeys('daily');
    browser.element(by.id('lastname')).sendKeys('daily');
    browser.element(by.id('userid')).sendKeys('daily');
    browser.element(by.id('password')).sendKeys('daily');
    browser.element(by.id('register-submit-btn-id')).click();
    expect(browser.getCurrentUrl()).toContain('/login');   
  });
 
  it('should be able to login user and navigate to headlines screen', () => {    
    browser.element(by.id('userid')).sendKeys('daily');
    browser.element(by.id('password')).sendKeys('daily');
    browser.element(by.id('login-submit-btn-id')).click();
    expect(browser.getCurrentUrl()).toContain('/news/headlines');    
  });

  it('should be able to search for news', () => { 
    browser.element(by.id('search-btn-id')).click(); 
    expect(browser.getCurrentUrl()).toContain('/news/search');   
    browser.element(by.id('news-search-btn-id')).sendKeys('Space Photos of the Week: The Moon Needs Sunscreen');    
    browser.element(by.id('news-search-btn-id')).sendKeys(protractor.Key.ENTER);
    browser.driver.sleep(1000); 
    const searchItems = element.all(by.css('.news-thumbnail'));
    expect(searchItems.count()).toBe(2);
    for(let i=0; i<2; i+=1) {
      expect(searchItems.get(i).getText()).toContain('Space Photos of the Week: The Moon Needs Sunscreen');
    }   
    
  });
  
  it('should be able to add news to watchlist', async() => { 
    browser.driver.manage().window().maximize();    
    const searchItems = element.all(by.css('.news-thumbnail'));
    expect(searchItems.count()).toBe(2);
    searchItems.get(0).click();
    browser.driver.sleep(1000); 
    browser.element(by.css('.addNewsBtn')).click();
     
  });
	
	it('should be able to logout user', () => {    
    expect('http://localhost:4200/logout');    
  });

  
  
});
