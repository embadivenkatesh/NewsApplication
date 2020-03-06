import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { Observable } from 'rxjs/Observable';
import { News } from './news';

@Injectable()
export class NewsService {

  apiKey:string;
  watchlistEndPoint:string;
  categoryNewsEndPoint:string;
  topHeadLinesEndPoint:string;
  searchNewsEndPoint:string;

  
  constructor(private http: HttpClient) {
    console.log("NewsService>>constructor")
    this.http = http;
    this.apiKey = 'c62d42386121473089029b14f901d7e8';
    this.watchlistEndPoint='http://localhost:8081/api/news';
    this.categoryNewsEndPoint='https://newsapi.org/v2/top-headlines?category=';
    this.topHeadLinesEndPoint='https://newsapi.org/v2/top-headlines?country=in&apikey=';
    this.searchNewsEndPoint='https://newsapi.org/v2/everything?q=';

  }

  getNews(categoryType:string,searchKey:string,page=1):Observable<Array<News>>{
    var endpoint = `${this.topHeadLinesEndPoint}${this.apiKey}&page=${page}`;
    var headLinesEndpoint = `${this.topHeadLinesEndPoint}${this.apiKey}&page=${page}`;
    var categoryEndpoint = `${this.categoryNewsEndPoint}${searchKey}&apikey=${this.apiKey}&page=${page}`;
    if(categoryType == 'headlines') {
      endpoint = headLinesEndpoint;
    } else if (categoryType == 'category' && searchKey != null && searchKey.length>0) {
      endpoint = categoryEndpoint
    } 
    return this.http.get(endpoint).pipe(
      map(this.pickNewsResult)
    );
  }

  getSearchedNews(type:string,searchKey:string,page=1):Observable<Array<News>>{
    var endpoint;
    if(searchKey!=null && searchKey.length>0){
      endpoint= `${this.searchNewsEndPoint}${searchKey}&apiKey=${this.apiKey}&language=en&page=${page}`
    }
    return this.http.get(endpoint).pipe(
      map(this.pickNewsResult)      
    );
 }

  pickNewsResult(Response){
    return Response['articles'];
  }

  getTransLateNews(news): News{    
      news.id= news.id;
      news.author = news.author;
      news.title = news.title;
      news.description = news.description;
      news.url = news.url;
      news.urlToImage = news.urlToImage;
      news.content = news.content;
      return news;
  }
  addNewsToWatchList(news){    
    news = this.getTransLateNews(news);
    console.log("NewsService>>addNewsToWatchList>>NewsId:"+news.id);
    return this.http.post(this.watchlistEndPoint,news);    
  }

 deleteNewsFromWatchList(news){
  console.log("NewsService>>deleteNewsFromWatchList>>NewsId:"+news.id);
    const deleteNewsEndPoint = `${this.watchlistEndPoint}/${news.id}`;
    return this.http.delete(deleteNewsEndPoint);
  }

  getWatchListedNews():Observable<Array<News>> {
    const getNewsEndPoint = `${this.watchlistEndPoint}/mynews`;    
    return this.http.get<Array<News>>(getNewsEndPoint);
  }
  
}
