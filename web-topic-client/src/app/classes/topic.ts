import { Note } from "./note";

export class Topic {

    constructor(
        public id?:number,
        public description?:string,
        public timestamp?:Date,
        public notes?:Note[]
    ) {}
}
