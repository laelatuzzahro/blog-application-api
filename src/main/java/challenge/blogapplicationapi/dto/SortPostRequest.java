package challenge.blogapplicationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SortPostRequest {
    private Boolean sortByAuthor;
    private Boolean sortByTitle;

}
