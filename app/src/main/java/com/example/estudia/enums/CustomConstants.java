package com.example.estudia.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomConstants {
    public static final class EstudiaConstants {
        public static final String SHARED_PREFS = "shared_prefs";
        public static final String ATTRIBUTES_USER = "attributes";
        public static final String EMAIL_USER = "email";
        public static final String NAME_USER = "name";
        public static final String LAST_NAME_USER = "family_name";
        public static final String PHONE_USER = "phone_number";
        public static final String AGE_USER = "nickname";
        public static final String STRATUM_USER = "zoneinfo";
        public static final String GENDER_USER = "gender";
        public static final String PERSONALITY_1 = "personality_1";
        public static final String PERSONALITY_2 = "personality_2";
        public static final String PERSONALITY_3 = "personality_3";
        public static final String[] CUSTOMER_REGISTER_QUESTIONS = {"race", "race_type", "disabilities", "disabilities_type", "work", "work_occupation", "work_experience", "study_experience", "motivation"};
        public static final String[] CUSTOMER_REGISTER_PREFERENCES = {"program_type", "study_area", "program_modality", "mobility", "study_city", "time_availability"};
        public static final String[] ARRAY_RACES = {"Selecciona tu población", "Pueblos y comunidades indígenas", "Comunidades negras o afrocolombianas", "Comunidad raizal", "Pueblo Rom o Gitano"};
        public static final String[] ARRAY_DISABILITIES = {"Selecciona tu discapacidad", "Auditiva", "Física", "Intelectual", "Visual", "Sordoceguera", "Psicosocial", "Múltiple"};
        public static final String[] ARRAY_WORKS = {"Selecciona tu profesión", "abogado", "administrador", "agricultor", "albañil", "animador", "antropólogo", "arquitecto", "artesano", "bibliotecólogo", "biólogo",
                "botánico", "cajero", "carnicero", "carpintero", "cerrajero", "cirujano", "cocinero", "contador", "deshollinador", "economista", "ecólogo", "editor", "electricista", "enfermero", "escritor", "escultor",
                "exterminador", "filólogo", "filósofo", "fontanero", "frutero", "físico", "geógrafo", "historiador", "ingeniero", "lechero", "lingüista", "locutor", "matemático", "mecánico", "médico", "músico", "obrero",
                "paleontólogo", "panadero", "paramédico", "peluquero", "periodista", "pescador", "pintor", "plomero", "policía", "politólogo", "profesor", "psicoanalista", "psicólogo", "químico",
                "radiólogo", "repartidor", "sastre", "secretaria", "sociólogo", "soldador", "tornero", "traductor", "técnico en turismo", "vendedor", "vigilante"};
        public static final String[] ARRAY_STUDY = {"Selecciona tu estudio completado", "Primaria", "Secundaria", "Técnica", "Profesional"};
        public static final String[] ARRAY_EXPERIENCE = {"Selecciona tu experiencia", "Amateur", "Intermedia", "Avanzada"};
        public static final String[] ARRAY_PROGRAM_TYPE = {"Selecciona el tipo de programa", "Técnico", "Tecnólogo"};
        public static final String[] ARRAY_PROGRAM_MODALITY = {"Selecciona la modalidad de estudio", "Presencial", "Virtual", "A distancia"};
        public static final String[] ARRAY_STUDY_AREAS = {"Selecciona tú área de estudio preferida", "Actividad física, recreación y deporte", "Acuícola y pesca", "Aeroespacial", "Agrícola", "Ambiental", "Artes gráficas",
                "Artesanías", "Automotor", "Biotecnología", "Comercio y ventas", "Construcción", "Cuero, calzado y marroquinería", "Cultura", "Electrónica y automatización", "Energía eléctrica", "Gestión administrativa y financiera",
                "Hidrocarburos", "Hotelería y turismo", "Informática, diseño y desarrollo de software", "Infraestructura", "Logística, gestión de la producción", "Materiales para la industria", "Mecánica industrial", "Minería",
                "Pecuaria", "Química aplicada", "Salud", "Servicios personales", "Telecomunicaciones", "Textil, confección, diseño y moda", "Transporte"};
        public static final String[] ARRAY_PREFERENCES = {"Tipo de Programa a estudiar", "Área de Conocimiento", "Modalidad de estudio", "Movilidad", "Disponibilidad horaria"};
        public static final String[] ARRAY_DESC_PREFERENCES = {"Por ejemplo, programa técnico o tecnólogo", "Profesión u ocupación a ejercer", "Virtual, presencial, a distancia", "Deseas desplazarte a otra ciudad", "Priorizas el horario en qué puedes tomar clase"};
        public static final String[] ARRAY_CITIES = {"Selecciona tu ciudad preferida", "Bogotá", "Cali", "Fusagasugá", "Medellín"};
        public static final String[] ARRAY_TIME_AVAILABILITY = {"Selecciona tu disponibilidad horaria", "Jornada Diurna (6:00 a.m., a 6:00 p.m.)", "Jornada Nocturna (6:00 p.m., a 10:00 p.m.)",
                "Jornada Madrugada (10:00 p.m., a 6:00 a.m.)", "Jornada Mixta (6:00 a.m., a 10:00 p.m.)"};
        public static final String SPINNER = "spinner";
        public static final String SWITCH = "switch";
        public static final String TEXT_EDIT = "text";
        public static final int REALISTA = 0;
        public static final int INVESTIGADOR = 1;
        public static final int ARTISTA = 2;
        public static final int SOCIAL = 3;
        public static final int EMPRENDEDOR = 4;
        public static final int CONVENCIONAL = 5;
        public static final String[] PERSONALITIES = {"Realista", "Investigador", "Artista", "Social", "Emprendedor", "Convencional"};
    }
}
