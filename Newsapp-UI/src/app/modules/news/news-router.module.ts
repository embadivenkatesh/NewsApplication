import {Routes,RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import { ContainerComponent } from './components/container/container.component';
import { NewsContainerComponent } from './components/news-container/news-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { AuthguardService } from '../../authguard.service';

const newsRoutes: Routes = [
    {
        path: 'news',
        children:[
                {
                    path:   '',
                    redirectTo: '/news/headlines',
                    pathMatch: 'full',
                    canActivate : [AuthguardService]
                },
                {
                    path:   'headlines',
                    component: NewsContainerComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'headlines',
                        searchKey: ''
                    }
                },
                {
                    path:   'business',
                    component: NewsContainerComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'category',
                        searchKey: 'business'
                    }
                } , 
                {
                    path:   'sports',
                    component: NewsContainerComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'category',
                        searchKey: 'sports'
                    }
                } ,
                {
                    path:   'science',
                    component: NewsContainerComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'category',
                        searchKey: 'science'
                    }
                } , 
                {
                    path:   'entertainment',
                    component: NewsContainerComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'category',
                        searchKey: 'entertainment'
                    }
                } , 
                {
                    path:   'health',
                    component: NewsContainerComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'category',
                        searchKey: 'health'
                    }
                } ,             
                {
                    path:   'watchlist',
                    component: WatchlistComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'watchlist'
                    }
                } ,
                {
                    path:   'search',
                    component: SearchComponent,
                    canActivate : [AuthguardService],
                    data:{
                        newsType: 'search'
                    }
                } 
            
            ]
    }    
];

@NgModule({
    imports:[RouterModule.forChild(newsRoutes)],
    exports:[RouterModule],
    providers:[],
    declarations:[]
})

export class NewsRouterModule {

}
