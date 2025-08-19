import { CommonModule } from '@angular/common';
import {
  Component,
  EventEmitter,
  Output,
  OutputEmitterRef,
} from '@angular/core';
import { Post, PostService } from '../../../services/post.service';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-post-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './post-form.component.html',
  styleUrl: './post-form.component.css',
})
export class PostFormComponent {
  @Output() postAdded = new EventEmitter<void>();
  postForm!: FormGroup;

  constructor(private fb: FormBuilder, private postService: PostService) {}

  ngOnInit() {
    this.postForm = this.fb.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
    });
  }
  onSubmit() {
    if (this.postForm.valid) {
      const newPost: Post = {
        id: 0, //it will be generated in the backend
        title: this.postForm.value.title!,
        content: this.postForm.value.content!,
        comments: [],
      };
      this.postService.createPost(newPost).subscribe(() => {
        this.postForm.reset();
        this.postAdded.emit(); //call the parent to upload the list
      });
    }
  }
}
