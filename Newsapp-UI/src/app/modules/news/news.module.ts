import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { NewsService } from  './news.service';
import { MatButtonModule } from '@angular/material/button';
import { ContainerComponent } from './components/container/container.component';
import { NewsRouterModule } from './news-router.module';
import { MatCardModule } from '@angular/material/card';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { NewsContainerComponent } from './components/news-container/news-container.component';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptor } from './tokeninterceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';



@NgModule({
  imports: [
    CommonModule,MatButtonModule,NewsRouterModule,MatDialogModule,MatCardModule,MatSnackBarModule,FormsModule,MatInputModule
  ],
  declarations: [ThumbnailComponent, ContainerComponent, WatchlistComponent,
     NewsContainerComponent, SearchComponent],

  providers:[NewsService,{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi:true
  }],
  
  //entryComponents : [NewsDialogComponent],
  
  exports: [ThumbnailComponent,ContainerComponent,SearchComponent]

})
export class NewsModule { 
    constructor(){   
      console.log("NewsModule>>constructor");   
    }

}
