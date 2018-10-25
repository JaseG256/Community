import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MatButtonModule, MatToolbarModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule,
    MatCardModule, MatProgressSpinnerModule, MatDialogModule } from '@angular/material';
import { from } from "rxjs";


@NgModule({
    imports: [CommonModule, MatButtonModule, MatToolbarModule, MatInputModule, MatTableModule, MatPaginatorModule,
         MatSortModule, MatCardModule, MatProgressSpinnerModule, MatDialogModule],
    exports: [CommonModule, MatButtonModule, MatToolbarModule, MatInputModule, MatTableModule, MatPaginatorModule,
        MatSortModule, MatCardModule, MatProgressSpinnerModule, MatDialogModule]
})
export class CustomMaterialModule {

}
