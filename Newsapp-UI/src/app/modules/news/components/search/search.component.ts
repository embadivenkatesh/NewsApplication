import { Component, OnInit ,Input } from '@angular/core';
import { News } from '../../news';
import { NewsService } from  '../../news.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'news-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
//  @Input()
  newsList: Array<News>;
  constructor(private newsService:NewsService) { }

  ngOnInit() {
  }
  onEnter(searchKey){
    this.newsService.getSearchedNews('search',searchKey).subscribe((newsList)=>{
      this.newsList= newsList;
    })
  }

}
