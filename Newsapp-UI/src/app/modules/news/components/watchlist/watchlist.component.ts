import { Component, OnInit, Input } from '@angular/core';
import { News } from '../../news';
import { NewsService } from '../../news.service';

@Component({
  selector: 'news-watchlist',
  template: `<news-container [newsList]="newsList" [useWatchListAPI]="useWatchListAPI"></news-container>`,
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  @Input()
  newsList: Array<News>;
  useWatchListAPI =true;

  constructor(private newsService:NewsService) {
    this.newsList=[];
   }

  ngOnInit() {
    this.newsService.getWatchListedNews()
    .subscribe((newsList)=> {
      this.newsList.push(...newsList);
    });
    
  }

}
