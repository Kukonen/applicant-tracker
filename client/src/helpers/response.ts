import {Candidate} from "./candidate.ts";

export interface responseParams {
    status: string,
    applicants: Candidate[];
}