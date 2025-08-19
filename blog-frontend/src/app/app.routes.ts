import { Routes } from '@angular/router';
import { PostListComponent } from './components/post/post-list/post-list.component';
import { PostFormComponent } from './components/post/post-form/post-form.component';

export const routes: Routes = [
  { path: '', component: PostListComponent },
  { path: 'new post', component: PostFormComponent },
];
