import { Component, OnInit,Input } from '@angular/core';
import { News } from '../../news';
import { NewsService } from '../../news.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'news-api-container',
  template: `<news-container [newsList]="newsList"></news-container>`,
  styles: []
})
export class NewsContainerComponent implements OnInit {
  @Input()
  newsList: Array<News>;
  newsType: string;
  searchKey: string;

  constructor(private newsService: NewsService ,private route: ActivatedRoute) {
    
    this.newsList=[];
    this.route.data.subscribe(data =>{
        this.newsType = data.newsType;
        this.searchKey = data.searchKey;
    });
  }
    ngOnInit() {
       this.newsService.getNews(this.newsType,this.searchKey).subscribe(newsList => {
        this.newsList.push(...newsList);
        console.log('newsList'+this.newsList);
});
     }

}
