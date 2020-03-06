import { Component, OnInit, Input } from '@angular/core';
import { News } from '../../news';
import { NewsService } from '../../news.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'news-container',
  templateUrl: 'container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  newsList: Array<News>;
  @Input()
  useWatchListAPI: boolean;

  constructor(private newsService: NewsService, private snackBar: MatSnackBar) {

  }

  ngOnInit() {

  }

  addToWatchList(news) {
    console.log("ContainerComponent>>addToWatchList>>started");
    let message = `${news.title} Added to WatchList`;
    console.log('message:' + message);
    this.newsService.addNewsToWatchList(news)
      .subscribe((news) => {
        this.snackBar.open(message, '', {
          duration: 1000
        });
      }); console.log("ContainerComponent>>addToWatchList>>ended");
  }

  deleteNewsFromWatchList(news) {
    
    console.log("ContainerComponent>>deleteNewsFromWatchList>>started");
    let message = `${news.title} deleted from WatchList`;
    console.log('message:' + message);    
    for (var index = 0; index < this.newsList.length; index++) {      
      if (this.newsList[index].title == news.title) {
        this.newsList.splice(index, 1);
      }
    }

    this.newsService.deleteNewsFromWatchList(news)
      .subscribe((news) => {
        this.snackBar.open(message, '', {
          duration: 1000
        });
      });
    console.log("ContainerComponent>>deleteNewsFromWatchList>>ended");
  }
   
}
