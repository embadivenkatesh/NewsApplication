import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { News } from '../../news';
//import { NewsDialogComponent } from '../news-dialog/news-dialog.component';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'news-thumbnail',
  templateUrl: 'news.thumbnail.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  news: News;
  @Input()
  useWatchListAPI: boolean;



  @Output()
  addNews = new EventEmitter();

  @Output()
  deleteNews = new EventEmitter();


  constructor(private dialog: MatDialog) {
    console.log('ThumbnailComponent>>consturtor')
  }

  ngOnInit() {

  }

  addToWatchList() {
    console.log("ThumbnailComponent>>addToWatchList>>started");
    this.addNews.emit(this.news);
    console.log("ThumbnailComponent>>addToWatchList>>ended");
  }

  deleteFromWatchList() {
    console.log("ThumbnailComponent>>deleteFromWatchList>>started");
    this.deleteNews.emit(this.news);
    console.log("ThumbnailComponent>>deleteFromWatchList>>ended");
  }
  /*
  updateWatchList(actionType) {
    console.log("ThumbnailComponent>>updateWatchList>>started>>actionType:" + actionType);
    let dialogRef = this.dialog.open(NewsDialogComponent, {
      width: '400px',
      data: { obj: this.news, actionType: actionType }
    });

    console.log('opend dialog');

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog was closed');
    });


    console.log("ThumbnailComponent>>updateWatchList>>ended");
  }
  */

}
