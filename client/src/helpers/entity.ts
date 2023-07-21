export interface Applicant {
    id: number,
    snils: string;
    name?: string;
}

export interface Candidate {
    id: number;
    applicant_id: number;
    program_id: number;
    score: number;
    priority?: number;
    is_certificate_submitted: boolean,
}

export interface Program {
    id: number;
    university: string;
    places: number;
    name: string;
    form: string;
    type: string;
}